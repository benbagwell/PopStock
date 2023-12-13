package com.nashss.se.popstock.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.nashss.se.popstock.activity.requests.GetAllWarehousesRequest;
import com.nashss.se.popstock.activity.results.GetAllWarehousesResult;

public class GetAllWarehousesLambda
        extends LambdaActivityRunner<GetAllWarehousesRequest, GetAllWarehousesResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetAllWarehousesRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetAllWarehousesRequest> input,
                                        Context context) {
        return super.runActivity(
            () -> input.fromUserClaims(claims ->
                    GetAllWarehousesRequest.builder()
                            .withUserId(claims.get("email"))
                            .build()),
            (request, serviceComponent) ->
                    serviceComponent.provideGetAllWarehousesActivity().handleRequest(request)
        );
    }
}
