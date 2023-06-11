#![allow(non_snake_case)]
#![cfg_attr(
    all(not(debug_assertions), target_os = "windows"),
    windows_subsystem = "windows"
)]

use rand::Rng;
use std::num::Wrapping;

// Reccomended default number of rounds
const NUMBER_OF_ROUNDS: u32 = 32;

// Magic number specified by the algorithm
const DELTA: Wrapping<u32> = Wrapping(0x9E3779B9);

#[tauri::command]
fn generate_key() -> String {
    // Generate 128 bits random key
    let mut rng = rand::thread_rng();

    const CHARSET: &[u8] = b"ABCDEFGHIJKLMNOPQRSTUVWXYZ
                            abcdefghijklmnopqrstuvwxyz
                            0123456789)(*&^%$#@!~";

    const KEY_LEN: usize = 16; // 16 * 8 = 128 bits
    let key: String = (0..KEY_LEN)
        .map(|_| CHARSET[rng.gen_range(0..CHARSET.len())] as char)
        .collect();
    key
}

fn key_to_u32_wrapped(key: &str) -> [Wrapping<u32>; 4] {
    // convert 128-bit key to 32-bit array
    let mut buffer = [0u8; 4];
    let key_bytes = key.as_bytes();
    let mut key_bytes_u32 = [0u32; 4];
    for (index, i) in (0..16).step_by(4).enumerate() {
        buffer[..].copy_from_slice(&key_bytes[i..(i + 4)]);
        key_bytes_u32[index] = u32::from_ne_bytes(buffer);
    }

    [
        Wrapping(key_bytes_u32[0]),
        Wrapping(key_bytes_u32[1]),
        Wrapping(key_bytes_u32[2]),
        Wrapping(key_bytes_u32[3]),
    ]
}

#[tauri::command]
fn encrypt(plaintext: &str, key: &str) -> String {
    encrypt_data(plaintext, &key_to_u32_wrapped(key))
}

fn encrypt_data(plaintext: &str, key: &[Wrapping<u32>; 4]) -> String {
    let plaintext_bytes = plaintext.as_bytes().to_vec();
    let plaintext_bytes_len = plaintext_bytes.len();

    // convert plaintext to 32 bit vec
    let mut buffer = [0u8; 4];
    let mut plaintext_bytes_u32: Vec<u32> = Vec::with_capacity(plaintext_bytes_len / 4);
    for i in (0..plaintext_bytes_len).step_by(4) {
        buffer[..].copy_from_slice(&plaintext_bytes[i..(i + 4)]);
        plaintext_bytes_u32.push(u32::from_le_bytes(buffer));
    }
    let blocks = (plaintext_bytes_len + 1) / 8; // number of 64 bits (8-byte) block in data
    let mut cipher: Vec<u32> = Vec::with_capacity(blocks / 2);
    let mut i = 0;
    for _ in 0..blocks {
        let (v0, v1) = encrypt_64(&[plaintext_bytes_u32[i], plaintext_bytes_u32[i + 1]], key);
        cipher.push(v0);
        cipher.push(v1);
        i += 2;
    }

    // encode cipher to hex then in decrypt_data decode hex into u32
    let mut output = String::with_capacity(plaintext_bytes_len);
    for i in cipher.iter() {
        output.push_str(&hex::encode(i.to_le_bytes()));
    }
    output
}

fn encrypt_64(data: &[u32; 2], key: &[Wrapping<u32>; 4]) -> (u32, u32) {
    let mut v0 = Wrapping(data[0]);
    let mut v1 = Wrapping(data[1]);
    let mut sum = Wrapping(0u32);

    for _ in 0..NUMBER_OF_ROUNDS {
        v0 += (((v1 << 4) ^ (v1 >> 5)) + v1) ^ (sum + key[(sum.0 & 3) as usize]);
        sum += DELTA;
        v1 += (((v0 << 4) ^ (v0 >> 5)) + v0) ^ (sum + key[((sum.0 >> 11) & 3) as usize]);
    }
    (v0.0, v1.0)
}

#[tauri::command]
fn decrypt(cipher: &str, key: &str) -> String {
    decrypt_data(cipher, &key_to_u32_wrapped(key))
}

fn decrypt_data(cipher: &str, key: &[Wrapping<u32>; 4]) -> String {
    let cipher_hex_decoded = hex::decode(cipher).unwrap();

    // convert u8 to 32 bit vec
    let mut buffer = [0u8; 4];
    let len = cipher_hex_decoded.len();
    let mut cipher_hex_decoded_bytes_u32: Vec<u32> = Vec::with_capacity(len / 4);
    for i in (0..len).step_by(4) {
        buffer[..].copy_from_slice(&cipher_hex_decoded[i..(i + 4)]);
        cipher_hex_decoded_bytes_u32.push(u32::from_le_bytes(buffer));
    }

    let blocks = cipher_hex_decoded_bytes_u32.len() / 2; // number of 64 bits (8-byte) block in data
    let mut output = String::with_capacity(cipher_hex_decoded_bytes_u32.len());
    let mut i = 0;
    for _ in 0..blocks {
        let (v0, v1) = decrypt_64(
            &[
                cipher_hex_decoded_bytes_u32[i],
                cipher_hex_decoded_bytes_u32[i + 1],
            ],
            key,
        );
        output.push_str(std::str::from_utf8(&v0.to_ne_bytes()).unwrap());
        output.push_str(std::str::from_utf8(&v1.to_ne_bytes()).unwrap());
        i += 2;
    }
    output
}

fn decrypt_64(data: &[u32; 2], key: &[Wrapping<u32>; 4]) -> (u32, u32) {
    let mut v0 = Wrapping(data[0]);
    let mut v1 = Wrapping(data[1]);
    let mut sum = DELTA * Wrapping(NUMBER_OF_ROUNDS);

    for _ in 0..NUMBER_OF_ROUNDS {
        v1 -= (((v0 << 4) ^ (v0 >> 5)) + v0) ^ (sum + key[((sum.0 >> 11) & 3) as usize]);
        sum -= DELTA;
        v0 -= (((v1 << 4) ^ (v1 >> 5)) + v1) ^ (sum + key[(sum.0 & 3) as usize]);
    }

    (v0.0, v1.0)
}

fn main() {
    tauri::Builder::default()
        .invoke_handler(tauri::generate_handler![encrypt, decrypt, generate_key])
        .run(tauri::generate_context!())
        .expect("error while running tauri application");
}
