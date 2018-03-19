package io.kotlintest.specs

import io.kotlintest.AbstractSpec
import io.kotlintest.TestCase
import io.kotlintest.TestContainer

abstract class DescribeSpec(body: DescribeSpec.() -> Unit = {}) : AbstractSpec() {

  init {
    body()
  }

  fun describe(name: String, init: DescribeScope.() -> Unit) {
    val descriptor = TestContainer("Describe: $name", this@DescribeSpec)
    rootContainer.addContainer(descriptor)
    DescribeScope(descriptor).init()
  }

  inner class DescribeScope(private val parent: TestContainer) {

    fun describe(name: String, init: DescribeScope.() -> Unit) {
      val descriptor = TestContainer("Describe: $name", this@DescribeSpec)
      parent.addContainer(descriptor)
      DescribeScope(descriptor).init()
    }

    fun it(name: String, test: () -> Unit): TestCase {
      val tc = TestCase(name, this@DescribeSpec, test, defaultTestCaseConfig)
      parent.addTest(tc)
      return tc
    }
  }
}