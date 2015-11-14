class HexToBase64 {
	public static void main(String[] args) throws NumberFormatException {
		String base64Index = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
		int i, j;
		byte tmpByte;

		byte padding = ((4 * args[0].length()) % 6 != 0) ? (byte)(6 - ((4 * args[0].length()) % 6)) : 0;
		boolean[] bitArray = new boolean[(4 * args[0].length()) + padding];

		for (i = 0; i < args[0].length(); i++) {
			tmpByte = Byte.parseByte(args[0].substring(i, i + 1), 16);
			for (j = 0; j < 4; j++) {
				bitArray[(4 * i) + j] = (((tmpByte & (0xF >> j)) >> (3 - j)) == 1) ? true : false;
			}
		}
		for (i = 4 * i; i < bitArray.length; i++) {
			bitArray[i] = false;
		}

		StringBuffer base64Output = new StringBuffer();
		for (i = 0; i < bitArray.length; i += 6) {
			tmpByte = 0;
			for (j = 0; j < 6; j++) {
				tmpByte += (bitArray[i + j] ? 1 : 0) << (5 - j);
			}
			base64Output.append(base64Index.charAt(tmpByte));
		}

		if (padding == 2) {
			base64Output.append("=");
		} else if (padding == 4) {
			base64Output.append("==");
		}

		System.out.print(base64Output);
	}
}
