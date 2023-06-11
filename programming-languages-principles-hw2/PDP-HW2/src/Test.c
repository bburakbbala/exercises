#include "Banka.h"

int main(void) {
    Banka banka = BankaOlustur("Ziraat Bankasi");
    Musteri musteri = MusteriOlustur("Burak", "Bala");
    banka->bankaMusteriEkle(banka, musteri);
    banka->bankaYazdir(banka);
    musteri->musteriYazdir(musteri);
    banka->emirleriIsle(banka, musteri);
    musteri->musteriYoket(musteri);
    banka->bankaYoket(banka);
    return 0;
}
