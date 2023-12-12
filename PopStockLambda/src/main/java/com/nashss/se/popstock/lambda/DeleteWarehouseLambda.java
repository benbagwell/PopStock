package com.nashss.se.popstock.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.popstock.activity.requests.DeleteWarehouseRequest;
import com.nashss.se.popstock.activity.results.DeleteWarehouseResult;


public class DeleteWarehouseLambda extends LambdaActivityRunner<DeleteWarehouseRequest, DeleteWarehouseResult>
        implements RequestHandler<AuthenticatedLambdaRequest<DeleteWarehouseRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<DeleteWarehouseRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    DeleteWarehouseRequest unauthenticatedRequest = input.fromPath(path -> DeleteWarehouseRequest.builder()
                            .withWarehouseId(path.get("warehouseId"))
                            .build());

                    return input.fromUserClaims(claims ->
                            DeleteWarehouseRequest.builder()
                                    .withUserId(claims.get("emails"))
                                    .withWarehouseId(unauthenticatedRequest.getWarehouseId())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideDeleteWarehouseActivity().handleRequest(request)
        );
    }
}
