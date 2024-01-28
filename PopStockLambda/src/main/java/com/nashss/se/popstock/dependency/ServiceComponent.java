package com.nashss.se.popstock.dependency;

import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.popstock.activity.*;

import com.nashss.se.popstock.activity.requests.GetTransactionReportRequest;
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

    GetTransactionReportActivity provideGetTransactionReportActivity();

}
