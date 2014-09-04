package presentation

import com.m3.octoparts.model.config.PartParam
import org.apache.commons.lang.StringEscapeUtils

/**
 * View adapter for a PartParam.
 */
case class ParamView(param: PartParam) {
  def id: Option[Long] = param.id

  def requiredCls: String = if (required) "required" else "optional"

  def name: String = param.outputName

  def paramType = param.paramType.toString

  def required = param.required

  def versioned = param.versioned

  def inputNameJs = StringEscapeUtils.escapeJavaScript(param.inputName)
}
