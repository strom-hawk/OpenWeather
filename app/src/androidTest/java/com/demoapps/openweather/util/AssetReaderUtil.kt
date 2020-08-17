package com.demoapps.openweather.util

import android.content.Context
import com.fasterxml.jackson.databind.util.ByteBufferBackedOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.RuntimeException

open class AssetReaderUtil{
    companion object{
        private val BUFFER_SIZE = 4*1024

        fun asset(context: Context, assetPath: String): String{
            try{
                val inputStream = context.classLoader.getResourceAsStream("response/$assetPath")
                return inputStreamToString(inputStream, "UTF-8")
            } catch (e: IOException){
                e.printStackTrace()
                throw RuntimeException(e)
            }
        }

        @Throws(IOException::class)
        fun inputStreamToString(inputStream: InputStream, charsetName: String): String{
            val stringBuffer = StringBuffer()
            val reader = InputStreamReader(inputStream, charsetName)
            val buffer = CharArray(BUFFER_SIZE)
            val length: Int = reader.read(buffer)
            stringBuffer.append(buffer, 0, length)
            return stringBuffer.toString()
        }
    }
}