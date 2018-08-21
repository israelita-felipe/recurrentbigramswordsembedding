package fonetica;

public class BigramVector {

	private Double v1x;
	private Double v1y;
	private Double v2x;
	private Double v2y;

	public BigramVector(Double[][] bigram) {
		this.v1x = bigram[0][0];
		this.v1y = bigram[0][1];

		this.v2x = bigram[1][0];
		this.v2y = bigram[1][1];
	}

	public BigramVector(Double[] v1, Double[] v2) {
		this.v1x = v1[0];
		this.v1y = v1[1];

		this.v2x = v2[0];
		this.v2y = v2[1];
	}

	public Double[][] getBigram() {
		return new Double[][] { new Double[] { v1x, v1y }, new Double[] { v2x, v2y } };
	}

	public Double getDet() {
		return new Double((v1x * v2y) - (v1y * v2x));
	}

	public Double getV1x() {
		return v1x;
	}

	public void setV1x(Double v1x) {
		this.v1x = v1x;
	}

	public Double getV1y() {
		return v1y;
	}

	public void setV1y(Double v1y) {
		this.v1y = v1y;
	}

	public Double getV2x() {
		return v2x;
	}

	public void setV2x(Double v2x) {
		this.v2x = v2x;
	}

	public Double getV2y() {
		return v2y;
	}

	public void setV2y(Double v2y) {
		this.v2y = v2y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((v1x == null) ? 0 : v1x.hashCode());
		result = prime * result + ((v1y == null) ? 0 : v1y.hashCode());
		result = prime * result + ((v2x == null) ? 0 : v2x.hashCode());
		result = prime * result + ((v2y == null) ? 0 : v2y.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BigramVector other = (BigramVector) obj;
		if (v1x == null) {
			if (other.v1x != null)
				return false;
		} else if (!v1x.equals(other.v1x))
			return false;
		if (v1y == null) {
			if (other.v1y != null)
				return false;
		} else if (!v1y.equals(other.v1y))
			return false;
		if (v2x == null) {
			if (other.v2x != null)
				return false;
		} else if (!v2x.equals(other.v2x))
			return false;
		if (v2y == null) {
			if (other.v2y != null)
				return false;
		} else if (!v2y.equals(other.v2y))
			return false;
		return true;
	}

}
