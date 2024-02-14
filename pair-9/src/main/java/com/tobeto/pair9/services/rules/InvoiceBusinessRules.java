package com.tobeto.pair9.services.rules;

import com.tobeto.pair9.core.utilities.exceptions.InvoiceBusinessException;
import com.tobeto.pair9.core.utilities.results.Messages;
import com.tobeto.pair9.repositories.InvoiceRepository;
import com.tobeto.pair9.services.abstracts.RentalService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Service
@AllArgsConstructor
public class InvoiceBusinessRules {

    private final InvoiceRepository invoiceRepository;
    private final RentalService rentalService;

    public String  isExistInvoiceByNumber(){
        String number = generateInvoiceNumber();
        while(invoiceRepository.existsInvoiceByInvoiceNo(number)){
            number = generateInvoiceNumber();
            return number;
        }
        return number;
    }

    public void isExistInvoiceById(Integer id){
        if(!invoiceRepository.existsById(id)){
            throw new InvoiceBusinessException(Messages.invoiceIsNotFound);
        }
    }

    public void isExistRentalById(Integer id){
        if(!rentalService.isExistRentalById(id)){
            throw new InvoiceBusinessException(Messages.rentalIsNotFound);
        }
    }

    public String generateInvoiceNumber(){
        Random random = new Random();
        int invoiceNumber = 100000 + random.nextInt(900000);
        return String.valueOf(invoiceNumber);
    }

    public boolean checkCardInformation(String cardNo,LocalDate expireDate,String cvv){
        String newCardNum = cardNo.replaceAll("[\\s-,]", "");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String formattedDate = expireDate.format(formatter);
        YearMonth yearMonth = YearMonth.parse(formattedDate, formatter);
        LocalDate inputDate = yearMonth.atDay(1);

        if(!(newCardNum.length() == 16 &&
                newCardNum.matches("\\d+") &&
                inputDate.isAfter(LocalDate.now().withDayOfMonth(1)) &&
                cvv.length() == 3 &&
                cvv.matches("\\d+"))){
            throw new InvoiceBusinessException(Messages.invoiceCardNumber);
        }
        return true;
    }

    public String maskCardNo(String cardNo){
       return cardNo.replaceAll("[\\s-,]", "")
                .replaceAll("(?<=\\d{4})\\d(?=\\d{4})", "*");
    }

    public String maskCvv(String cvv){
        return cvv.replaceAll("\\d(?=\\d{1})", "*");
    }
}
