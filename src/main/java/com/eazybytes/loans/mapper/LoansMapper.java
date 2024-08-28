package com.eazybytes.loans.mapper;

import com.eazybytes.loans.dto.LoansDto;
import com.eazybytes.loans.entity.Loans;

public class LoansMapper {
    public static Loans mapTLoans(LoansDto loansDto, Loans loans){
        loans.setAmountPaid(loansDto.getAmountPaid());
        loans.setLoanNumber(loansDto.getLoanNumber());
        loans.setLoanType(loansDto.getLoanType());
        loans.setMobileNumber(loansDto.getMobileNumber());
        loans.setOutstandingAmount(loansDto.getOutstandingAmount());
        loans.setTotalLoan(loansDto.getTotalLoan());

        return loans;
    }
    
    public static LoansDto mapTLoansDto(Loans loans, LoansDto loansDto){
        loansDto.setAmountPaid(loans.getAmountPaid());
        loansDto.setLoanNumber(loans.getLoanNumber());
        loansDto.setLoanType(loans.getLoanType());
        loansDto.setMobileNumber(loans.getMobileNumber());
        loansDto.setOutstandingAmount(loans.getOutstandingAmount());
        loansDto.setTotalLoan(loans.getTotalLoan());

        return loansDto;
    }
}
