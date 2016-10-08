package com.example

import akka.actor.{Actor, ActorSystem}

class CompletableApp(val steps:Int) extends App {
  val canComplete = new java.util.concurrent.CountDownLatch(1);
  val canStart = new java.util.concurrent.CountDownLatch(1);
  val completion = new java.util.concurrent.CountDownLatch(steps);

  val system = ActorSystem("eaipatterns")

  def awaitCanCompleteNow = canComplete.await

  def awaitCanStartNow = canStart.await

  def awaitCompletion = {
    completion.await
    system.shutdown()
  }

  def canCompleteNow() = canComplete.countDown()

  def canStartNow() = canStart.countDown()

  def completeAll() = {
    while (completion.getCount > 0) {
      completion.countDown()
    }
  }

  def completedStep() = completion.countDown()
}

abstract class RabbitMQReceiver extends Actor

object DatatypeChannel {

}

class ProductQueriesChannel extends RabbitMQReceiver {
  def receive = {
    case message: Array[Byte] =>
      val productQuery = translateToProductQuery(message)
  }

  def translateToProductQuery(message: Array[Byte]) = {
    null
  }
}

class PriceQuoteChannel extends RabbitMQReceiver {
  def receive = {
    case message: Array[Byte] =>
      val priceQuote = translateToPriceQuote(message)
  }

  def translateToPriceQuote(message: Array[Byte]) = {
    null
  }
}

class PurchaseOrderChannel extends RabbitMQReceiver {
  def receive = {
    case message: Array[Byte] =>
      val purchaseOrder = translateToPurchaseOrder(message)
  }

  def translateToPurchaseOrder(message: Array[Byte]) = {
    null
  }
}
