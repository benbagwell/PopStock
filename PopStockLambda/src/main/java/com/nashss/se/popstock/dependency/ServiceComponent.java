package com.nashss.se.popstock.dependency;

import com.nashss.se.popstock.activity.CreateItemActivity;
import com.nashss.se.popstock.activity.CreateWarehouseActivity;

import com.nashss.se.popstock.activity.GetAllWarehousesActivity;
import com.nashss.se.popstock.activity.GetWarehouseActivity;
import dagger.Component;

import javax.inject.Singleton;

/**
 * Dagger component for providing dependency injection in the Music Playlist Service.
 */
@Singleton
@Component(modules = {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {

    CreateItemActivity provideCreateItemActivity();

    CreateWarehouseActivity provideCreateWarehouseActivity();

    GetAllWarehousesActivity provideGetAllWarehousesActivity();

    GetWarehouseActivity provideGetWarehouseActivity();

}
