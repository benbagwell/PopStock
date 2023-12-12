package com.nashss.se.popstock.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.nashss.se.popstock.activity.requests.CreateWarehouseRequest;
import com.nashss.se.popstock.activity.results.CreateWarehouseResult;

public class CreateWarehouseLambda
        extends LambdaActivityRunner<CreateWarehouseRequest, CreateWarehouseResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateWarehouseRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateWarehouseRequest> input, Context context) {
        return super.runActivity(
            () -> {
                CreateWarehouseRequest unauthenticatedRequest = input.fromBody(CreateWarehouseRequest.class);
                return input.fromUserClaims(claims ->
                        CreateWarehouseRequest.builder()
                                .withUserId(claims.get("email"))
                                .withName(unauthenticatedRequest.getName())
                                .withRegion(unauthenticatedRequest.getRegion())
                                .build());
            },
            (request, serviceComponent) ->
                    serviceComponent.provideCreateWarehouseActivity().handleRequest(request)
        );
    }
}
