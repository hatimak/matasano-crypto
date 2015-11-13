class HexToBase64 {
	public static void main(String[] args) throws NumberFormatException {
		final String base64Index = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

		String tmp = (args[0].length() % 2 == 0) ? args[0] : "0" + args[0];
		int z = tmp.length() / 2;
		int[] hexInput = new int[z];
		while (--z >= 0) {
			hexInput[z] = ((Integer.parseInt(tmp.substring(2 * z, (2 * z) + 1), 16) << 4) + Integer.parseInt(tmp.substring(2 * z + 1, (2 * z) + 2), 16));
		}

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
