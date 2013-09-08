package com.twitter.t02uk.scrapingutil

import scala.xml.NodeSeq

class NodeSeqExt(self: NodeSeq) {
  // has
  def has(attribute: String)(value: String): NodeSeq = {
    self filter (_ \ ("@" + attribute) flatMap(_.toString.split("""\s""")) contains value)
  }
  
  // hasChildWith
  def hasChildWith(attribute: String)(value: String): NodeSeq = {
    new NodeSeqExt(self \\ "_").has(attribute)(value)
  }

  // .class selector
  def \* = has("class") _
  // #id selector
  def \# = has("id") _
  // [name="name"] selector
  def \@ = has("name") _

  // .class selector (recursive and might be very slow)
  def \\* = hasChildWith("class") _
  // #id selector (recursive and might be very slow)
  def \\# = hasChildWith("id") _
  // [name="name"] selector (recursive and might be very slow)
  def \\@ = hasChildWith("name") _

}

object NodeSeqExt {
  implicit def nodeSeq2nodeSeqExt(nodeSeq: NodeSeq): NodeSeqExt = {
    new NodeSeqExt(nodeSeq)
  }
}