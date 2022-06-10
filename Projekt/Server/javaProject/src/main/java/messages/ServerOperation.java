package messages;

/**
 *
 * @author Konrad Basa
 * @author Marcin Bonar
 * @author Piotr Bielecki
 *
 */

//Operacje obsługiwane przez serwer

public enum ServerOperation {
    verifyLogin, //sprawdzenie poprawności loginu i hasła

    //---Kierownik---
    addPracownik,       //dodanie pracownika
    showPracownicy,     //pokazanie pracowników
    deletePracownik,    //usunięcie pracownika
    showPositions,      //pokazanie stanowisk
    newSalary,          //aktualizacja wynagrodzenia
    showTypeRoom,       //pokazanie typow pokoi
    showAllRoom,        //pokazanie pokoi
    addRoom,            //dodanie pokoju
    updateRoomPrice,    //zmiana ceny pokoju
    addSchedule,        //dodanie grafiku
    showSchedule,       //wyswietlenie grafiku
    showstatistics,     //wyswietlenie statystyk hotelu
    showStarsOpinion,   //pobranie sredniej oceny gwiazdkowej
    showOpinions,       //wyswietlenie wszystkich opinii o hotelu
    showForMess,        //wyswietlenie formularzy kontaktowych
    showComplaints,     //wyswietlenie skarg\
    freeRoom,           //zwolnienie pokoju

    //---Pracownik---
    showAmenities,      //wyswitlenie udogodnien
    addAmenities,       //dodawanie udogodnien
    deleteAmenities,    //usuwanie udogodnien
    addPromotion,       //dodawanie promocji
    addPromRoom,        //dodanie danych do tabeli promocja_pokoj
    showPromotion,      //wyswietlenie listy promocji
    deletePromotion,    //usuwanie promocji
    promotionList,      //wyszukanie listy promocji
    showReservation,    //wyswietlenie rezerwacji
    cancellationMyAmenities, //anulowanie udogodnienia ktore wybral klient
    addResAmenities,    //dodanie nowego udogodnienia do rezerwacji
    cancellationRes,    //Anulowanie rezerwacji
    updateResTime,      //zmiana czasu pobytu

    //---Klient---
    addReservation,     //dodanie rezerwacji
    showMyAmenities,    //wyswietlenie udogodnien ktore dotycza danej rezerwacji
    showPosAmenities,   //wyswietlenie udogodnien mozliwych do wyboru
    addOpinion,         //dodanie nowej opinii
    addConForm,         //wyslanie formularza kontaktowego
    showResCom,         //wyswietlenie rezerwacji dotyczacych klienta
    addComplaint,       //złożenie skargi na pobyt

    disconnect,         //rozłączenie klienta
}


