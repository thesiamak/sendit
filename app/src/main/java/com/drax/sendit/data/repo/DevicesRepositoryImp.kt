package com.drax.sendit.data.repo

import com.drax.sendit.data.db.DevicesDao
import com.drax.sendit.data.db.model.Device
import com.drax.sendit.domain.repo.DevicesRepository


open class DevicesRepositoryImp(private val devicesDao: DevicesDao): DevicesRepository {
    override fun addDevice(device: Device) = devicesDao.add(device)
    override fun removeDevice(deviceId: String)  = devicesDao.deleteById(deviceId)
    override fun updateDevice(device: Device) = devicesDao.update(device)
    override fun getAllDevices() = devicesDao.getList()
    override fun getAllDevicesSync() = devicesDao.getAll()
}