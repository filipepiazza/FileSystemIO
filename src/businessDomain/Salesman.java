package businessDomain;

public class Salesman {

	private String cpf;
	private String name;
	private Double salary;

	public Salesman() {
	}

	public Salesman(String cpf, String name, Double salary) {
		super();
		this.cpf = cpf;
		this.name = name;
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Salesman [cpf=" + cpf + ", name=" + name + ", salary=" + salary + "]";
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

}
