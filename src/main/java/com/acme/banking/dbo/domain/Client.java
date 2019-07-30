package com.acme.banking.dbo.domain;

import java.util.*;

public class Client {
    private UUID id;
    private String name;
    private Collection<UUID> accountIds = new ArrayList<>();

    public Client(UUID id, String name) {
        if (id == null || name == null) throw new IllegalArgumentException();
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addAccount(Account account) {
        if (!account.getClientId().equals(id) || accountIds.contains(account.getId())) {
            throw new IllegalArgumentException();
        }

        accountIds.add(account.getId());
    }

    public void removeAccount(Account account) {
        if (!account.getClientId().equals(id)) {
            throw new IllegalArgumentException();
        }

        accountIds.remove(account.getId());
    }

    public Collection<UUID> getAccountIds() {
        return Collections.unmodifiableCollection(accountIds);
    }

}
