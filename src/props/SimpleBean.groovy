package props

public class SimpleBean {

	def String name

	def int age

	public String toJson() {
		return "{\"name\":\"" + getName() + "\", \"age\":" + getAge() + "}"
	}
}
