class HexToBase64 {
	public static void main(String[] args) {
		final String base64Index = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
		byte[] hexInput = args[0].getBytes();
		StringBuffer base64Output = new StringBuffer("");
		int ungroup = hexInput.length % 3, set, i, j;

		for (i = 0; i < hexInput.length - ungroup; i += 3) {
			set = (hexInput[i] << 16) + (hexInput[i + 1] << 8) + hexInput[i + 2];
			for (j = 0; j < 4; j++) {
				base64Output.append(base64Index.charAt((set & (0xFC0000 >> (6 * j))) >> 6 * (3 - j)));
			}
		}
		if (ungroup == 1) {
			set = hexInput[i] << 4;
			for (j = 0; j < 2; j++) {
				base64Output.append(base64Index.charAt((set & (0xFC0 >> (6 * j))) >> 6 * (1 - j)));
			}
			base64Output.append("==");
		} else if (ungroup == 2) {
			set = ((hexInput[i] << 8) + hexInput[i + 1]) << 2;
			for (j = 0; j < 3; j++) {
				base64Output.append(base64Index.charAt((set & (0x3F000 >> (6 * j))) >> 6 * (2 - j)));
			}
			base64Output.append("=");
		}

		System.out.print(base64Output);
	}
}
