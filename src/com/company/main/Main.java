package com.company.main;

import com.company.check_in_request.checkInRequest;

/* -- Parameters IN----------------------
- IdRequest (9 characters)
- IdSender (9 characters)
- DateRequest (LocalDateTime)
- Price (float)
- Valuta ("RUB")
- TypeRequest ("ESIA", "SMEV", "INTERNAL")
- N symbols DescriptionRequest (max 10)
- DescriptionRequest (N characters)

    Example:
        123454321
        555665414
        2022-02-13 11:30
        1500.30
        RUB
        ESIA
        5
        Заявка на предоставление земельного участка

 */

public class Main {

    public static void main(String[] args) {

        checkInRequest currentRequest = new checkInRequest();
        currentRequest.checkParam(args);

    }
}
