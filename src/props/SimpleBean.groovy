package props

class SimpleBean {

	def String name

	def int age

	def toJson() {
		"{\"name\":\"" + getName() + "\", \"age\":" + getAge() + "}"
	}
}
