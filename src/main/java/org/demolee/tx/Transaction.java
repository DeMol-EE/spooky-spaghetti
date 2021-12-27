package org.demolee.tx;

// This special syntax tricks json-b into serializing the record's properties because it thinks they are getters
public record Transaction (
    String getUseCase,
    String getName,
    String getRawBody,
    Object[] getParameters
){}