package cn.huat.util;

import java.util.UUID;

public class UUIDGenerator {
	public static String get16UUID() {
		String randomUUID = UUID.randomUUID().toString().replaceAll("-", "");
		String substring = randomUUID.substring(randomUUID.length()-16, randomUUID.length());
		return substring;
	}
	public static String get32UUID() {
		String randomUUID = UUID.randomUUID().toString().replace("-", "");
		return randomUUID;
	}
}