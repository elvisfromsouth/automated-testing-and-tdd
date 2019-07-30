package com.acme.banking.dbo.domain;

import java.util.UUID;

public class SavingAccount implements Account {
    private UUID id;
    private Client client;
    private double amount;

    public SavingAccount(UUID id, Client client, double amount) {
        if (id == null || client == null || amount < 0d) throw new IllegalArgumentException();
        this.id = id;
        this.amount = amount;
        this.client = client;

        this.client.addAccount(this);
    }

    public Client getClient() {
        return client;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public UUID getClientId() {
        if (client == null) {
            throw new IllegalStateException("Account is closed. There is no client.");
        }

        return client.getId();
    }

    @Override
    public void close() {
        client.removeAccount(this);
        client = null;
    }
}
