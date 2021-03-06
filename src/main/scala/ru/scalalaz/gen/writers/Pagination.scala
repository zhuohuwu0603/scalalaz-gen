/*
 * Copyright 2016 Scalalaz Podcast Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ru.scalalaz.gen.writers

case class Pagination[A](prev: Option[A], current: A, next: Option[A])

object Pagination {

  def forList[A](list: List[A]): List[Pagination[A]] =
    forList[A](None, list)

  private def forList[A](prev: Option[A], list: List[A]): List[Pagination[A]] = {
    list match {
      case Nil => Nil
      case x :: Nil =>
        List(Pagination(prev, x, None))
      case x :: y :: _ =>
        List(Pagination(prev, x, Some(y))) ++ forList(Some(x), list.tail)
    }
  }

}
