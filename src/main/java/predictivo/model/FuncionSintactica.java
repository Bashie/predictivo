package predictivo.model;

public enum FuncionSintactica {
	VERVO(0), SUSTANTIVO(1), ADJETIVO(2);

	private Integer dbValue;

	private FuncionSintactica(Integer dbValue) {
		this.dbValue = dbValue;
	}
	
	public Integer getDbValue() {
        return this.dbValue;
    }
	
	public static FuncionSintactica fromDbValue(Integer dbValue) {
        switch (dbValue) {
		case 0:
			return VERVO;
		case 1:
			return SUSTANTIVO;
		case 2:
			return ADJETIVO;
		}
		return VERVO;		
    }
}
