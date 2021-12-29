package org.demolee.tx;

public record Transaction (
    String useCase,
    String name,
    String rawBody
){}