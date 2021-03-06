package controllers

import _root_.presentation.NavbarLinks
import com.m3.octoparts.aggregator.service.PartsService
import com.m3.octoparts.repository.{ ConfigsRepository, MutableConfigsRepository }
import controllers.hystrix.HystrixController
import controllers.system.{ HealthcheckController, SystemConfigController }
import play.api.Configuration
import scaldi.Module

import scala.concurrent.duration._

class ControllersModule extends Module {

  bind[PartsController] to {
    val partsService = inject[PartsService]
    val configsRepository = inject[ConfigsRepository]
    val requestTimeout = inject[Int](identified by "timeouts.asyncRequestTimeout").millis
    val readClientCacheHeaders = {
      val disableFlag = inject[Boolean](identified by "read-client-cache.disabled")
      !disableFlag
    }
    new PartsController(partsService, configsRepository, requestTimeout, readClientCacheHeaders)
  }

  bind[CacheController] to injected[CacheController]

  bind[AdminController] to new AdminController(inject[MutableConfigsRepository])(inject[NavbarLinks])

  bind[NavbarLinks] to NavbarLinks(
    kibana = inject[Configuration].getString("urls.kibana"),
    hystrixDashboard = inject[Configuration].getString("urls.hystrixDashboard"),
    swaggerUI = inject[Configuration].getString("urls.swaggerUI"),
    wiki = inject[Configuration].getString("urls.wiki")
  )

  // System controllers
  bind[HealthcheckController] to injected[HealthcheckController]
  bind[SystemConfigController] to new SystemConfigController(inject[Configuration].underlying)
  bind[HystrixController] to new HystrixController()

}
