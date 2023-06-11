using Clinic.Models;

namespace Clinic.DataAccess.Repository.IRepository
{
    public interface IDoctorTreatmentRepository : IRepositoryAsync<DoctorTreatment>
    {
        void Update(DoctorTreatment doctorTreatment);
    }
}