package it.granz

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class FunStackTest {
    @Test
    fun `push into the stack`() {
        val stack1 = FunStack<Char>()
        val stack2 = stack1.push('A')
        expectThat(stack1.size()).isEqualTo(0)
    }

    @Test
    fun `push push pop`(){
        val (b, stack) = FunStack<Char>()
            .push('A')
            .push('B')
            .pop()
        expectThat(stack.size()).isEqualTo(1)
        expectThat(b).isEqualTo('B')
    }
}
