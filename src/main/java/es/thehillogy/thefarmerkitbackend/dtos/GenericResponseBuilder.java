package es.thehillogy.thefarmerkitbackend.dtos;

public final class GenericResponseBuilder<T>
{
	private String status = "OK";
	private String code;
	private String message;
	private T data;

	public GenericResponseBuilder(T data)
	{
		this.data = data;
	}

	public GenericResponseBuilder<T> withStatus(String status)
	{
		this.status = status;
		return this;
	}

	public GenericResponseBuilder<T> withCode(String code)
	{
		this.code = code;
		return this;
	}

	public GenericResponseBuilder<T> withMessage(String message)
	{
		this.message = message;
		return this;
	}

	public GenericResponseBuilder<T> withData(T data)
	{
		this.data = data;
		return this;
	}

	public GenericResponse<T> build()
	{
		return new GenericResponse<>(this.status, this.code, this.message, this.data);
	}
}
