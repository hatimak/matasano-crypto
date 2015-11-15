class FixedXOR {
	public static void main(String[] args) {
		int bufferLength = args[0].length();
		boolean[] bitArray1 = new boolean[4 * bufferLength], bitArray2 = new boolean[4 * bufferLength];
		int i, j;
		byte tmpByte1, tmpByte2;
		for (i = 0; i < bufferLength; i++) {
			tmpByte1 = Byte.parseByte(args[0].substring(i, i + 1), 16);
			tmpByte2 = Byte.parseByte(args[1].substring(i, i + 1), 16);
			for (j = 0; j < 4; j++) {
				bitArray1[(4 * i) + j] = (((tmpByte1 & (0xF >> j)) >> (3 - j)) == 1) ? true : false;
				bitArray2[(4 * i) + j] = (((tmpByte2 & (0xF >> j)) >> (3 - j)) == 1) ? true : false;
			}
		}

		for (i = 0; i < 4 * bufferLength; i++) {
			bitArray1[i] = bitArray1[i] ^ bitArray2[i];
		}

		String hexIndex = "0123456789abcdef";
		StringBuffer hexOutput = new StringBuffer();
		for (i = 0; i < bufferLength; i++) {
			tmpByte1 = 0;
			for (j = 0; j < 4; j++) {
				tmpByte1 += (byte)((bitArray1[(4 * i) + j] ? 1 : 0) << (3 - j));
			}
			hexOutput.append(hexIndex.substring(tmpByte1, tmpByte1 + 1));
		}
		System.out.print(hexOutput);
	}
}
