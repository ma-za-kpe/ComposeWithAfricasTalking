package com.maku.core.network.exception

import java.io.IOException

class NetworkUnavailableException(
    message: String = "Please connect to the internet :("
) : IOException(message)

class OtherCustomException(
    message: String = "Other custom exception message :("
) : IOException(message)
