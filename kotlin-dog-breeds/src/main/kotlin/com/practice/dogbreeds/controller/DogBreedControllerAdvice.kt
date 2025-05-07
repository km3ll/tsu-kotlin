package com.practice.dogbreeds.controller

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
@ResponseBody
class DogBreedControllerAdvice {
    @ExceptionHandler(InvalidBreedException::class)
    fun handleInvalidBreed(
        ex: InvalidBreedException,
        request: HttpServletRequest,
    ): ResponseEntity<ErrorResponse> {
        val error =
            ErrorResponse(
                message = ex.message.orEmpty(),
                path = request.requestURI,
            )
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .contentType(MediaType.APPLICATION_JSON)
            .body<ErrorResponse>(error)
    }

    @ExceptionHandler(BreedNotFoundException::class)
    fun handleBreedNotFound(
        ex: BreedNotFoundException,
        request: HttpServletRequest,
    ): ResponseEntity<ErrorResponse> {
        val error =
            ErrorResponse(
                message = ex.message.orEmpty(),
                path = request.requestURI,
            )
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .contentType(MediaType.APPLICATION_JSON)
            .body<ErrorResponse>(error)
    }

    @ExceptionHandler(InternalErrorException::class)
    fun handleInternalError(
        ex: InternalErrorException,
        request: HttpServletRequest,
    ): ResponseEntity<ErrorResponse> {
        val error =
            ErrorResponse(
                message = ex.message.orEmpty(),
                path = request.requestURI,
            )
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .contentType(MediaType.APPLICATION_JSON)
            .body<ErrorResponse>(error)
    }

    @ExceptionHandler(Exception::class)
    fun handleOtherErrors(
        ex: Exception,
        request: HttpServletRequest,
    ): ResponseEntity<ErrorResponse> {
        val error =
            ErrorResponse(
                message = ex.message.orEmpty(),
                path = request.requestURI,
            )
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .contentType(MediaType.APPLICATION_JSON)
            .body<ErrorResponse>(error)
    }
}
