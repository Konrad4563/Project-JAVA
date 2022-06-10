package messages;

 //Operacje obsługiwane przez serwer musza byc takie same po stronie klienta jak i servera

/**
 *
 * @author Konrad Basa
 * @author Marcin Bonar
 * @author Piotr Bielecki
 *
 */

public enum ServerOperation {
    verifyLogin,        //sprawdza czy login i haslo sa poprawne

    //Kierownik
    addPracownik,       //dodaje pracownika
    showPracownicy,     //wyswietla pracownikow
    showPositions,      //wyswietla stanowiska
    deletePracownik,    //usuwa pracownika
    newSalary,          //zmienia pensje pracownika
    showTypeRoom,       //pokazanie pokoi
    showAllRoom,        //pokazanie pokoi
    addRoom,            //dodanie pokoju
    updateRoomPrice,    //zmiana ceny pokoju
    addSchedule,        //dodanie grafiku
    showSchedule,       //wyswietlenie grafiku
    showstatistics,     //wyswietlenie statystyk hotelu
    showStarsOpinion,   //pobranie sredniej oceny gwiazdkowej
    showOpinions,       //wyswietlenie wszystkich opinii o hotelu
    showForMess,        //wyswietlenie formularzy kontaktowych
    showComplaints,     //wyswietlenie skarg
    freeRoom,           //zwolnienie pokoju

    //Pracownik
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

    //Klient
    addReservation,     //dodanie rezerwacji
    showMyAmenities,    //wyswietlenie wybranych udogodnien
    showPosAmenities,   //wyswietlenie udogodnien mozliwych do wyboru
    addOpinion,         //dodanie nowej opinii
    addConForm,         //wyslanie formularza kontaktowego
    showResCom,         //wyswietlenie rezerwacji dotyczacych klienta
    addComplaint,       //złożenie skargi na pobyt

    disconnect,         //rozłączenie klienta
}
