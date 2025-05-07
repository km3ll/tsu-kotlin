package com.practice.dogbreeds.controller

class InvalidBreedException(
    message: String,
) : RuntimeException(message)

class BreedNotFoundException(
    message: String,
) : RuntimeException(message)

class InternalErrorException(
    message: String,
) : RuntimeException(message)
