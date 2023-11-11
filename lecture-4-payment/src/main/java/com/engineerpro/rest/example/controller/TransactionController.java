package com.engineerpro.rest.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.engineerpro.rest.example.dto.CreateTransactionRequest;
import com.engineerpro.rest.example.dto.CreateTransactionResponse;
import com.engineerpro.rest.example.dto.GetUserBalanceRequest;
import com.engineerpro.rest.example.dto.GetUserBalanceResponse;
import com.engineerpro.rest.example.dto.GetUserTransactionsRequest;
import com.engineerpro.rest.example.dto.GetUserTransactionsResponse;
import com.engineerpro.rest.example.service.TransactionService;
import com.engineerpro.rest.example.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {
	private final TransactionService transactionService;

	@GetMapping("/{userId}")
	public ResponseEntity<GetUserTransactionsResponse> getUserBalance(@PathVariable Integer userId) {

		final GetUserTransactionsResponse response = transactionService
				.getUserTransactions(GetUserTransactionsRequest.builder().userId(userId).build());

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PostMapping
	public ResponseEntity<CreateTransactionResponse> createTransaction(
			@Valid @RequestBody CreateTransactionRequest registrationRequest) {
		final CreateTransactionResponse response = transactionService.createTransaction(registrationRequest);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
