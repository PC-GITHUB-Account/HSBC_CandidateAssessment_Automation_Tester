package resources;

public enum APIResources {
	
	LatestForeignExchangeRates ("/api/latest"),
	SpecificDateForeignExchangeRates("/api/2021-01-01");

	private String resource;
	
	APIResources(String resource)
	{
		this.resource=resource;
	}
	
	public String getResource()
	{
		return resource;
	}

}
