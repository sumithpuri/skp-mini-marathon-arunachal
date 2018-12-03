package me.sumithpuri.github.arunachal.spring.dynamic.sample;

import org.springframework.beans.factory.DisposableBean;

public interface Account {

	public int getAccountId();
	
	public long getAccountAmt();
	
	public String getAccountName();
}
