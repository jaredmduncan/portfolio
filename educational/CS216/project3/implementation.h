// Linker errors if not included with header.
template<typename T> void GenericRecord<T>::setVar(T varP)
{
	this->var = varP;
}

// Linker errors if not included with header.
template<typename T> T GenericRecord<T>::getVar()
{
	return this->var;
}