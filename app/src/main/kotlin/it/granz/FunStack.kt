package it.granz

class FunStack<T> private constructor(private val list: List<T>) {
    constructor(): this(listOf())

    fun push(element: T): FunStack<T> = FunStack(list + element)

    fun size(): Int = list.size

    fun pop(): Pair<T, FunStack<T>> {
        return Pair(list.last(), FunStack(list.subList(0, list.size - 1)))
    }
}
