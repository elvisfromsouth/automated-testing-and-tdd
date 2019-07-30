package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.UUID;

import static org.fest.assertions.api.Assertions.assertThat;

public class ClientTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test // BDD = AT + DDD
    public void shouldSavePropertiesWhenCreated() {
        //region fixture | given | arrang
        UUID stubId = UUID.randomUUID();
        String dummyClientName = "dummy client name";
        //endregion

        //region when | act
        Client sut = new Client(stubId, dummyClientName);
        //endregion

        //region then | assert
        assertThat(sut.getId())
                .isEqualTo(stubId);

        assertThat(sut.getName())
                .isEqualTo(dummyClientName);

        //endregion
    }

    @Test
    public void shouldContainAccountIdWhenAddAccount() {
        Client stubClient = new Client(UUID.randomUUID(), "dummy client name");
        SavingAccount savingAccount = new SavingAccount(UUID.randomUUID(), stubClient, 100);

        assertThat(stubClient.getAccountIds())
                .contains(savingAccount.getId());
    }

    @Test
    public void shouldNotContainAccountIdWhenAccountRemoved() {
        Client stubClient = new Client(UUID.randomUUID(), "dummy client name");
        SavingAccount savingAccount = new SavingAccount(UUID.randomUUID(), stubClient, 100);

        stubClient.removeAccount(savingAccount);

        assertThat(stubClient.getAccountIds())
                .doesNotContain(savingAccount.getId());
    }

    @Test
    public void shouldThrowExceptionWhenAddAccountWithDuplicateId() {
        exception.expect(IllegalArgumentException.class);

        Client stubClient = new Client(UUID.randomUUID(), "dummy client name");
        SavingAccount savingAccount = new SavingAccount(UUID.randomUUID(), stubClient, 100);

        stubClient.addAccount(savingAccount);
    }

    @Test
    public void shouldThrowExceptionWhenAddAccountWithOtherClient() {
        exception.expect(IllegalArgumentException.class);

        Client sutClient = new Client(UUID.randomUUID(), "dummy client name");
        Client stubClient = new Client(UUID.randomUUID(), "dummy client name");
        SavingAccount savingAccount = new SavingAccount(UUID.randomUUID(), stubClient, 100);

        sutClient.addAccount(savingAccount);
    }

    @Test
    public void shouldThrowExceptionWhenRemoveOtherClient() {
        exception.expect(IllegalArgumentException.class);

        Client sutClient = new Client(UUID.randomUUID(), "dummy client name");
        Client stubClient = new Client(UUID.randomUUID(), "dummy client name");
        SavingAccount savingAccount = new SavingAccount(UUID.randomUUID(), stubClient, 100);

        sutClient.removeAccount(savingAccount);
    }

    @Test
    public void shouldThrowExceptionWhenNullUUIDGivenToConstructor() {
        exception.expect(IllegalArgumentException.class);

        UUID stubId = null;
        String name = "dummy name";

        new Client(stubId, name);
    }

    @Test
    public void shouldThrowExceptionWhenNullNameGivenToConstructor() {
        exception.expect(IllegalArgumentException.class);

        UUID stubId = UUID.randomUUID();
        String name = null;

        new Client(stubId, name);
    }

    @Test
    public void shouldThrowExceptionWhenNullArgsGivenToConstructor() {
        exception.expect(IllegalArgumentException.class);

        UUID stubId = null;
        String name = null;

        new Client(stubId, name);
    }
}
