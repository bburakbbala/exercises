const { invoke } = window.__TAURI__.tauri;

let plaintextInputEl;
let ciphertextInputEl;
let keyInputEl1;
let keyInputEl2
let keyMsgEl;
let encryptedMsgEl;
let decryptedMsgEl;

async function generateKey() {
  const key = await invoke("generate_key");
  keyMsgEl.textContent = key;
  keyInputEl1.value = key;
  keyInputEl2.value = key;
}

async function encrypt() {
  const plaintext = plaintextInputEl.value;
  const key = keyInputEl2.value;
  if ( plaintext === "" ||  key === "") {
    alert("Empty field(s)");
    return;
  }
  if (plaintext.length < 8 || plaintext.length % 8 !== 0) {
    alert("Input length is not satisfied");
    return;
  }
  encryptedMsgEl.textContent = await invoke('encrypt', {
		plaintext: plaintext,
		key: keyInputEl1.value
  });
}

async function decrypt() {
  if (plaintextInputEl.value === '' || keyInputEl2.value === '') {
		alert('Empty field(s)');
		return;
  }
  if (plaintext.length < 8 && plaintext.length % 8 !== 0) {
		alert('Input length is not satisfied');
		return;
	}
	decryptedMsgEl.textContent = await invoke('decrypt', {
		cipher: ciphertextInputEl.value,
    key: keyInputEl2.value
	});
}

window.addEventListener("DOMContentLoaded", () => {
  plaintextInputEl = document.querySelector('#plaintext-input');
  ciphertextInputEl = document.querySelector('#ciphertext-input');
  keyInputEl1 = document.querySelector('#key-input1');
  keyInputEl2 = document.querySelector('#key-input2');
  keyMsgEl = document.querySelector('#key');
  encryptedMsgEl = document.querySelector('#cipher');
  decryptedMsgEl = document.querySelector('#plaintext');
  document
		.querySelector('#generate-key-button')
		.addEventListener('click', () => generateKey());
  document
    .querySelector("#encrypt-button")
    .addEventListener("click", () => encrypt());
  document
    .querySelector("#decrypt-button")
    .addEventListener("click", () => decrypt());
});
