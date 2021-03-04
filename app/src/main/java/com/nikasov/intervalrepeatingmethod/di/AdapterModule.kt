package com.nikasov.intervalrepeatingmethod.di

import android.content.Context
import com.nikasov.intervalrepeatingmethod.ui.adapter.pager.CarouselAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AdapterModule {
    @Provides
    fun provideCarouselAdapter(@ApplicationContext context: Context): CarouselAdapter {
        return CarouselAdapter(context)
    }
}