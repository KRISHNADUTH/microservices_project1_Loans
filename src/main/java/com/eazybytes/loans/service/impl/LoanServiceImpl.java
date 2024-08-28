package com.eazybytes.loans.service.impl;

import com.eazybytes.loans.service.ILoansService;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eazybytes.loans.constants.LoansConstants;
import com.eazybytes.loans.dto.LoansDto;
import com.eazybytes.loans.entity.Loans;
import com.eazybytes.loans.exception.LoanAlreadyExistsException;
import com.eazybytes.loans.exception.ResourceNotFoundException;
import com.eazybytes.loans.mapper.LoansMapper;
import com.eazybytes.loans.repository.LoansRepository;

@Service
public class LoanServiceImpl implements ILoansService {

    @Autowired
    LoansRepository loansRepository;

    @Override
    public void createLoan(String mobileNumber) {

        Optional<Loans> optionalLoans = loansRepository.findByMobileNumber(mobileNumber);
        if (optionalLoans.isPresent())
            throw new LoanAlreadyExistsException("Loan already registered with given mobileNumber " + mobileNumber);

        Loans loans = new Loans();

        loans.setAmountPaid(0);
        loans.setLoanNumber(String.valueOf(100000000000L + new Random().nextInt(900000000)));
        loans.setLoanType(LoansConstants.HOME_LOAN);
        loans.setMobileNumber(mobileNumber);
        loans.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        loans.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        loansRepository.save(loans);
    }

    @Override
    public LoansDto fetchLoan(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Loans", "mobileNumber", mobileNumber));
        LoansDto loansDto = LoansMapper.mapTLoansDto(loans, new LoansDto());
        return loansDto;
    }

    @Override
    public boolean updateLoan(LoansDto loansDto) {
        Loans loans = loansRepository.findByLoanNumber(loansDto.getLoanNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "Loan Number", loansDto.getLoanNumber()));
        LoansMapper.mapTLoans(loansDto, loans);
        loansRepository.save(loans);
        return true;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Loans", "mobileNumber", mobileNumber));
        loansRepository.delete(loans);
        return true;
    }

}
