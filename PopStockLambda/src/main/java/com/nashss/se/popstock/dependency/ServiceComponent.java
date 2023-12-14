package com.nashss.se.popstock.dependency;

import com.nashss.se.popstock.activity.*;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {

    CreateItemActivity provideCreateItemActivity();

    CreateWarehouseActivity provideCreateWarehouseActivity();

    GetAllWarehousesActivity provideGetAllWarehousesActivity();

    GetWarehouseActivity provideGetWarehouseActivity();

    UpdateWarehouseActivity provideUpdateWarehouseActivity();

    DeleteWarehouseActivity provideDeleteWarehouseActivity();

    GetItemsActivity provideGetItemsActivity();

    CreateTransactionActivity provideCreateTransactionActivity();

}
