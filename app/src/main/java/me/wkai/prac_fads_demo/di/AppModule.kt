package me.wkai.prac_fads_demo.di

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.wkai.prac_fads_demo.data.model.adapter.VectorAdapter
import me.wkai.prac_fads_demo.data.api.ApiConst.BASE_URL
import me.wkai.prac_fads_demo.data.api.VectorApi
import me.wkai.prac_fads_demo.data.model.Vector
import me.wkai.prac_fads_demo.data.model.VectorResponse
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

	//==Retrofit:Api==
	@Provides
	@Singleton
	fun provideRetrofitBuilder(moshi:Moshi): Retrofit.Builder {
		return Retrofit.Builder()
			.baseUrl(BASE_URL)
			.addConverterFactory(ScalarsConverterFactory.create())
			.addConverterFactory(MoshiConverterFactory.create(moshi))
	}

	@Provides
	@Singleton
	fun provideVectorApi(builder:Retrofit.Builder): VectorApi {
		return builder
			.build()
			.create(VectorApi::class.java)
	}

	//==Moshi:Adapter==
	@Provides
	@Singleton
	fun provideMoshi():Moshi {
		return Moshi.Builder()
			.add(VectorAdapter())
			.build()
	}

	@Provides
	@Singleton
	fun provideVectorAdapter(moshi:Moshi):JsonAdapter<Vector> {
		return moshi.adapter(Vector::class.java)
	}

	@Provides
	@Singleton
	fun provideVectorReponseAdapter(moshi:Moshi):JsonAdapter<VectorResponse> {
		return moshi.adapter(VectorResponse::class.java)
	}

}