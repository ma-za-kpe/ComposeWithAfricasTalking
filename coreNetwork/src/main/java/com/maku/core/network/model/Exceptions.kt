package com.maku.core.network.model

import java.io.IOException

class NetworkUnavailableException(
    message: String = "Please connect to the internet :("
) : IOException(message)

class NetworkException(
    message: String
) : Exception(message)
