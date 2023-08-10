package com.ultra.sample.core.base

abstract class UseCase<in P, R> {

    suspend operator fun invoke(parameters: P): R = execute(parameters)

    protected abstract suspend fun execute(parameters: P): R
}