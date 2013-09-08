package com.twitter.t02uk.scrapingutil

import org.scalatest.FunSuite
import com.twitter.t02uk.scrapingutil.NodeSeqExt.nodeSeq2nodeSeqExt

class NodeSeqExtSuite extends FunSuite {

  val xml = <root>
<ul id="id1" name="name1" class="class1">
<li id="id1-1" name="name1-1" class="class1-1">1-1.</li>
<li id="id1-2" name="name1-2" class="class1-2">1-2.</li>
<li id="id1-3" name="name1-3" class="class1-3">1-3.</li>
</ul>
<ul id="id2" name="name2" class="class2">
<li id="id2-1" name="name2-1" class="class2-1">2-1.</li>
<li id="id2-2" name="name2-2" class="class2-2">2-2.</li>
<li id="id2-3" name="name2-3" class="class2-3">2-3.</li>
</ul>
</root>

  test("""\# test""") {
    assert((xml \ "ul" \ "li" \# "id1-1" text) === "1-1.")
  }
  test("""\* test""") {
    assert((xml \ "ul" \ "li" \* "class1-2" text) === "1-2.")
  }
  test("""\@ test""") {
    assert((xml \ "ul" \ "li" \@ "name1-3" text) === "1-3.")
  }

  test("""\\# test""") {
    assert((xml \\# "id1-1" text) === "1-1.")
  }
  test("""\\* test""") {
    assert((xml \\* "class1-2" text) === "1-2.")
  }
  test("""\\@ test""") {
    assert((xml \\@ "name1-3" text) === "1-3.")
  }
}