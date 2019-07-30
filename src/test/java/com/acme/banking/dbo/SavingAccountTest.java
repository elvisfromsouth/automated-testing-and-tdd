package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.UUID;

import static org.fest.assertions.api.Assertions.assertThat;

public class SavingAccountTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldSavePropertiesWhenCreated() {
        UUID stubId = UUID.randomUUID();
        Client stubClient = new Client(UUID.randomUUID(), "John");
        double stubAmount = 1d;

        SavingAccount sut = new SavingAccount(stubId, stubClient, stubAmount);

        assertThat(sut.getId())
                .isEqualTo(stubId);

        assertThat(sut.getClient())
                .isEqualTo(stubClient);

        assertThat(sut.getClient().getAccountIds())
                .contains(stubId);

        assertThat(sut.getAmount())
                .isEqualTo(stubAmount);

        assertThat(sut.getClientId())
                .isEqualTo(stubClient.getId());

    }

    @Test
    public void shouldThrowExceptionWhenIllegalArgsGivenToConstructor() {
        exception.expect(IllegalArgumentException.class);

        UUID stubId = null;
        Client stubClient = null;
        double stubAmount = 10d;

        new SavingAccount(stubId, stubClient, stubAmount);
    }

    @Test
    public void shouldThrowExceptionWhenNullIdGivenToConstructor() {
        exception.expect(IllegalArgumentException.class);

        UUID stubId = null;
        Client stubClient = new Client(UUID.randomUUID(), "John");
        double stubAmount = 10d;

        new SavingAccount(stubId, stubClient, stubAmount);
    }

    @Test
    public void shouldThrowExceptionWhenNullClientGivenToConstructor() {
        exception.expect(IllegalArgumentException.class);

        UUID stubId = UUID.randomUUID();
        Client stubClient = null;
        double stubAmount = 10d;

        new SavingAccount(stubId, stubClient, stubAmount);
    }

    @Test
    public void shouldThrowExceptionWhenNegativeAmountGivenToConstructor() {
        exception.expect(IllegalArgumentException.class);

        UUID stubId = UUID.randomUUID();
        Client stubClient = new Client(UUID.randomUUID(), "John");
        double stubAmount = -1d;

        new SavingAccount(stubId, stubClient, stubAmount);
    }

    @Test
    public void shouldClearClientWhenAccountClosed() {
        UUID stubId = UUID.randomUUID();
        Client stubClient = new Client(UUID.randomUUID(), "John");
        double stubAmount = 1d;

        SavingAccount sut = new SavingAccount(stubId, stubClient, stubAmount);
        sut.close();

        assertThat(sut.getClient()).isEqualTo(null);
        assertThat(stubClient.getAccountIds()).doesNotContain(stubId);
    }

    @Test
    public void shouldThrowExceptionWhenAccountIsClosedAndGetClientIdCalled() {
        UUID stubId = UUID.randomUUID();
        Client stubClient = new Client(UUID.randomUUID(), "John");
        double stubAmount = 1d;
        exception.expect(IllegalStateException.class);

        SavingAccount sut = new SavingAccount(stubId, stubClient, stubAmount);
        sut.close();

        sut.getClientId();
    }
}
