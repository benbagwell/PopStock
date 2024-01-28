package com.nashss.se.popstock.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.popstock.activity.requests.GetTransactionReportRequest;
import com.nashss.se.popstock.activity.results.GetTransactionReportResult;

import java.time.LocalDate;

public class GetTransactionReportLambda extends LambdaActivityRunner<GetTransactionReportRequest, GetTransactionReportResult> implements RequestHandler<AuthenticatedLambdaRequest<GetTransactionReportRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetTransactionReportRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    GetTransactionReportRequest unauthenticatedRequest = input.fromPath(path -> GetTransactionReportRequest.builder()
                            .withWarehouseId(path.get("warehouseId"))
                            .withStartDate(LocalDate.parse("startDate"))
                            .withEndDate(LocalDate.parse("endDate"))
                            .build());

                    return input.fromUserClaims(claims ->
                            GetTransactionReportRequest.builder()
                                    .withWarehouseId(unauthenticatedRequest.getWarehouseId())
                                    .withStartDate(unauthenticatedRequest.getStartDate())
                                    .withEndDate(unauthenticatedRequest.getEndDate())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideGetTransactionReportActivity().handleRequest(request)
        );
    }
}
