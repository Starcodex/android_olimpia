package com.starcodex.olimpia.di.module

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.starcodex.commons.BuildConfig.DOMAIN
import com.starcodex.data.auth.source.entity.AuthPrefs
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = arrayOf(DataModule::class))
class ApiModule {

    @Provides
    internal fun provideOkHttpClient(): OkHttpClient.Builder {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
    }

    @Provides
    internal fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create()
    }


    @Provides
    internal fun provideRetrofit(client: OkHttpClient.Builder, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://$DOMAIN/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(client.build())
            .build()
    }

    @Named("Auth")
    @Provides
    internal fun provideAuthRetrofit(client: OkHttpClient.Builder, gson: Gson, authPrefs: AuthPrefs ): Retrofit {
        client.addInterceptor(object: Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val original = chain.request()
                val request = original.newBuilder()
                    .header("Authorization","Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjNjY2IyY2JjMjMzZGJlYjMyMTBlODEyYWY4MWY2YzJiMWM0NDZlZjc3NmZhMzM5M2Q5MGM0YTU4ODhkYTA4ZjhhYWFhZjFiMjA4YjhmMjBmIn0.eyJhdWQiOiIxIiwianRpIjoiM2NjYjJjYmMyMzNkYmViMzIxMGU4MTJhZjgxZjZjMmIxYzQ0NmVmNzc2ZmEzMzkzZDkwYzRhNTg4OGRhMDhmOGFhYWFmMWIyMDhiOGYyMGYiLCJpYXQiOjE1OTUwODAyNzMsIm5iZiI6MTU5NTA4MDI3MywiZXhwIjoxNjEwOTc3ODczLCJzdWIiOiIyIiwic2NvcGVzIjpbXX0.TJDw9l4kfumykBgygxYC33M7DOdi8-PrrLYVMLCC2DMLt8d-_3wLpYqBncl07poUk32YOA9-7XD72GYOUiaP_EjI4_EtIIUKCMnH0SnbGm1UspX_mn_2G6AmtbSLUZMfiE8_59vxaGJq65lw4Al0rfHRJUa9Jvp1Y3Y8-rhTH6PFwWquBt5VfJqB1Bn3-MYdrZmRHr9JE7xhtJrt6OXWGPUE2pe0v1-KaWEJ9qxl1tNjw6YM7BGmwGYRkzClL6IjXvoW9U-KYCW3D03an_KmwKqV_2w62aFiDgdTdSizVnf-g6AOQlcVUpWPinBsT5HqyvUI-30lnXB7Wkcfa6va0nlygKalXg0DuPpfda7WM0wzoEjMxwZCWZn6FJG8OU9LrX6R3KV-_kww8CkGU1EZ0ucbKHs9zo4rH3BVMpRHXWLGgUrJ6OIs0yz-E0AV4RGUykduHPzGb4YHGgBlxx80KuXsqhW6YYc1ijxdDU94IDA40XWmnLJOY1m2rCOdqyj3tY93gZhejTtNAwtaFKTK8BkvWFUo2C698M3Ef38ymAI6JEcy2wTEsi5biEIjiP3pS1959rEtWtBtIHSt6Xz64wck5tDVazhwGeJIp3BOHffgmuqHk9Jc3lQRDCMZgdY5sZcHTp4-bxJVQJ20fNI7EKghBBIVXXKhHg-mOQnNeTY")
                    .method(original.method(), original.body())
                    .build()
                return chain.proceed(request)
            }
        })
        return Retrofit.Builder()
            .baseUrl("https://$DOMAIN/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(client.build())
            .build()
    }

}